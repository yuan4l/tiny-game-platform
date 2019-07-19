package cn.yuan.tiny.platform.core.sql;

import java.util.function.Supplier;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001915:57
 */
public abstract class AbstractSqlDataProvider<T extends AbstractSqlMapper> {
    protected T sqlMapper;

    protected AbstractSqlDataProvider(Supplier<T> sqlMapper) {
        this.sqlMapper = sqlMapper.get();
    }
}
