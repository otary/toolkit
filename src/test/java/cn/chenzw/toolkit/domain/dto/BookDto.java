package cn.chenzw.toolkit.domain.dto;

import cn.chenzw.toolkit.domain.Writeable;
import cn.chenzw.toolkit.domain.entity.Book;
import cn.chenzw.toolkit.support.SSO;

import java.math.BigDecimal;

@SSO
public class BookDto extends Book implements Writeable {

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "price=" + price +
                '}';
    }
}
