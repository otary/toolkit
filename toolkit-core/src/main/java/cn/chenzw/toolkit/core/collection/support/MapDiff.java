package cn.chenzw.toolkit.core.collection.support;

import cn.chenzw.toolkit.core.collection.MapKit;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chenzw
 */
public class MapDiff {

    public Map diff(Map left, Map right) {
        Map<String, Object[]> diffHolder = new HashMap<>();
        diff(left, right, diffHolder, "");

        // 只保留右边差异
        Map<String, Object[]> diffs = diffHolder.entrySet().stream()
                .filter(entry -> entry.getValue()[1] != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map map = new HashMap();
        for (Map.Entry<String, Object[]> entry : diffs.entrySet()) {
            MapKit.addDeepValue(map, entry.getKey().split("\\."), entry.getValue()[1]);
        }
        return map;
    }

    public Map flatDiff(Map left, Map right) {
        Map<String, Object[]> diffHolder = new HashMap<>();
        diff(left, right, diffHolder, "");
        return diffHolder;
    }

    private void diff(Map left, Map right, Map<String, Object[]> diffHolder, String cumulativeKey) {
        MapDifference difference = Maps.difference(left, right);
        for (Map.Entry entry : (Set<Map.Entry>) difference.entriesOnlyOnLeft().entrySet()) {
            String key = this.buildKey(cumulativeKey, entry.getKey().toString());
            if (entry.getValue() instanceof Map) {
                diff((Map) entry.getValue(), new HashMap(), diffHolder, key);
            } else {
                diffHolder.put(key, new Object[]{entry.getValue(), null});
            }
        }
        for (Map.Entry entry : (Set<Map.Entry>) difference.entriesOnlyOnRight().entrySet()) {
            String key = this.buildKey(cumulativeKey, entry.getKey().toString());
            if (entry.getValue() instanceof Map) {
                diff(new HashMap(), (Map) entry.getValue(), diffHolder, key);
            } else {
                diffHolder.put(key, new Object[]{null, entry.getValue()});
            }
        }

        for (Map.Entry entry : (Set<Map.Entry>) difference.entriesDiffering().entrySet()) {
            MapDifference.ValueDifference valueDiff = (MapDifference.ValueDifference) entry.getValue();
            String key = this.buildKey(cumulativeKey, entry.getKey().toString());
            if (valueDiff.leftValue() == null) {
                diffHolder.put(key, new Object[]{null, valueDiff.rightValue()});
            } else if (valueDiff.rightValue() == null) {
                diffHolder.put(key, new Object[]{valueDiff.leftValue(), null});
            } else if (valueDiff.leftValue() instanceof Map) {
                if (valueDiff.rightValue() instanceof Map) {
                    diff((Map) valueDiff.leftValue(), (Map) valueDiff.rightValue(), diffHolder, key);
                } else {
                    diffHolder.put(key, new Object[]{valueDiff.leftValue(), valueDiff.rightValue()});
                }
            } else if (valueDiff.leftValue().getClass().isArray()) {
                if (!Arrays.equals((Object[]) valueDiff.leftValue(), (Object[]) valueDiff.rightValue())) {
                    diffHolder.put(key, new Object[]{valueDiff.leftValue(), valueDiff.rightValue()});
                }
            } else {
                diffHolder.put(key, new Object[]{valueDiff.leftValue(), valueDiff.rightValue()});
            }
        }
    }

    private String buildKey(String cumulativeKey, String key) {
        return StringUtils.isEmpty(cumulativeKey) ? key : cumulativeKey + "." + key;
    }

}
