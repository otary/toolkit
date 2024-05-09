package cn.chenzw.toolkit.third.party.webdav.client.handler;

import cn.chenzw.toolkit.core.lang.ConvertKit;
import cn.chenzw.toolkit.third.party.webdav.entity.MultiStatusEntity;
import cn.chenzw.toolkit.third.party.webdav.exception.WebdavException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.apache.jackrabbit.webdav.DavException;
import org.apache.jackrabbit.webdav.DavServletResponse;
import org.apache.jackrabbit.webdav.MultiStatus;
import org.apache.jackrabbit.webdav.MultiStatusResponse;
import org.apache.jackrabbit.webdav.client.methods.BaseDavRequest;
import org.apache.jackrabbit.webdav.client.methods.HttpPropfind;
import org.apache.jackrabbit.webdav.property.DavProperty;
import org.apache.jackrabbit.webdav.property.DavPropertyName;
import org.apache.jackrabbit.webdav.property.DavPropertySet;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.*;

/**
 * @author chenzw
 */
public class MultiStatusResponseHandler implements ResponseHandler<MultiStatusEntity> {

    private static final List<String> PROBABLE_DATE_FORMATS = Arrays.asList(
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "EEE, dd MMM yyyy HH:mm:ss zzz",
            "yyyy-MM-dd'T'HH:mm:ss.sss'Z'",
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "EEE MMM dd HH:mm:ss zzz yyyy",
            "EEEEEE, dd-MMM-yy HH:mm:ss zzz",
            "EEE MMMM d HH:mm:ss yyyy"
    );

    private BaseDavRequest davRequest;

    public MultiStatusResponseHandler(BaseDavRequest davRequest) {
        this.davRequest = davRequest;
    }


    @Override
    public MultiStatusEntity handleResponse(HttpResponse response) {
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode != DavServletResponse.SC_MULTI_STATUS) {
            throw new WebdavException("request error!", statusCode, response.getStatusLine().getReasonPhrase());
        }
        MultiStatusEntity mse = new MultiStatusEntity();
        try {
            MultiStatus multistatus = this.davRequest.getResponseBodyAsMultiStatus(response);
            MultiStatusResponse[] responses = multistatus.getResponses();
            String rootPath = getRootPath(responses);
            for (MultiStatusResponse msp : responses) {
                DavPropertySet propertySet = msp.getProperties(HttpStatus.SC_OK);
                String displayName = getPropertyValue(propertySet, DavPropertyName.DISPLAYNAME, String.class);
                if (StringUtils.isEmpty(displayName)) {
                    displayName = StringUtils.replaceOnce(msp.getHref(), rootPath, "");
                }
                mse.getItems().add(
                        MultiStatusEntity.ResponseItem.builder()
                                .etag(getPropertyValue(propertySet, DavPropertyName.GETETAG, String.class))
                                .contentType(getPropertyValue(propertySet, DavPropertyName.GETCONTENTTYPE, String.class))
                                .contentLength(getPropertyValue(propertySet, DavPropertyName.GETCONTENTLENGTH, Long.class))
                                .contentLanguage(getPropertyValue(propertySet, DavPropertyName.GETCONTENTLANGUAGE, String.class))
                                .lastModified(getPropertyDateValue(propertySet, DavPropertyName.GETLASTMODIFIED))
                                .creationDate(getPropertyDateValue(propertySet, DavPropertyName.CREATIONDATE))
                                .displayName(displayName)
                                .type(getPropertyValue(propertySet, DavPropertyName.RESOURCETYPE, String.class) == null ? "file" : "directory")
                                .href(msp.getHref())
                                .build()
                );
            }
        } catch (DavException e) {
            throw new WebdavException(e.getMessage(), e.getErrorCode(), e.getStatusPhrase());
        }
        return mse;
    }

    private <T> T getPropertyValue(DavPropertySet propertySet, DavPropertyName propertyName, Type type) {
        DavProperty<?> davProperty = propertySet.get(propertyName);
        if (davProperty != null) {
            return ConvertKit.convert(type, davProperty.getValue());
        }
        return null;
    }

    private Date getPropertyDateValue(DavPropertySet propertySet, DavPropertyName propertyName) {
        DavProperty<?> davProperty = propertySet.get(propertyName);
        if (davProperty != null) {
            for (String dateFormat : PROBABLE_DATE_FORMATS) {
                Date date = null;
                try {
                    date = DateUtils.parseDate((String) davProperty.getValue(), Locale.US, dateFormat);
                } catch (ParseException e) {

                }
                if (date != null) {
                    return date;
                }
            }
        }
        return null;
    }

    private String getRootPath(MultiStatusResponse[] responses) {
        if (responses.length > 0) {
            return responses[0].getHref();
        }
        return null;
    }
}
