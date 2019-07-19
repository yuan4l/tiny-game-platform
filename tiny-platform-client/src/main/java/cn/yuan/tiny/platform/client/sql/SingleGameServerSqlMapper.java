package cn.yuan.tiny.platform.client.sql;

import cn.yuan.tiny.platform.core.sql.AbstractSqlMapper;
import cn.yuan.tiny.platform.core.sql.SqlEnvCache;
import org.apache.ibatis.session.SqlSession;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001917:37
 */
public class SingleGameServerSqlMapper extends AbstractSqlMapper {
    @Override
    protected SqlSession sqlSession() {
        return SqlEnvCache.getInstance().getDefault(SqlEnvs.GAME_SERVER).openSession();
    }
}
