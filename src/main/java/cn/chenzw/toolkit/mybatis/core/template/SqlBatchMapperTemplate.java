package cn.chenzw.toolkit.mybatis.core.template;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.function.Consumer;

/**
 * 批量提交模版
 *
 * @author chenzw
 * @since 1.0.1
 */
public class SqlBatchMapperTemplate {

    SqlSessionTemplate sqlSessionTemplate;

    public SqlBatchMapperTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    public <T> void execute(Class<T> mapper, Consumer<T> consumer) {
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            T targetMapper = session.getMapper(mapper);
            consumer.accept(targetMapper);
            session.commit();
            session.clearCache();
        } finally {
            session.close();
        }

    }


}
