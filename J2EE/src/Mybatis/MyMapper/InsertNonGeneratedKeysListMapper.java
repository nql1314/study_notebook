import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * 通用Mapper接口,特殊方法，批量插入，支持批量插入的数据库都可以使用，例如mysql,h2等
 *
 * @param <T> 不能为空
 * @author liuzh
 */
public interface InsertNonGeneratedKeysListMapper<T> {

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等
     *
     * @param recordList
     * @return
     */
    @Options(useGeneratedKeys = false)
    @InsertProvider(type = MySpecialProvider.class, method = "dynamicSQL")
    int insertNonGeneratedKeysList(List<T> recordList);
}