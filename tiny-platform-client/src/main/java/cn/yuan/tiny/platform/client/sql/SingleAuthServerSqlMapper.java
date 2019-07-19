package cn.yuan.tiny.platform.client.sql;

import cn.yuan.tiny.platform.core.sql.AbstractSqlMapper;
import cn.yuan.tiny.platform.core.sql.SqlEnvCache;
import org.apache.ibatis.session.SqlSession;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001917:34
 */
public class SingleAuthServerSqlMapper extends AbstractSqlMapper {
    @Override
    protected SqlSession sqlSession() {
        return SqlEnvCache.getInstance().getDefault(SqlEnvs.AUTH_SERVER).openSession();
    }
}
