package com.yhl.f22.frame.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yhl.f22.frame.db.po.impl.QmcMsgs;
import com.yhl.f22.frame.db.po.impl.QmcMsgsExample;

public interface QmcMsgsMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    int countByExample(QmcMsgsExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    int deleteByExample(QmcMsgsExample example);

    /**
     * 批量条件查询,支持大字段类型
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    List<QmcMsgs> selectByExampleWithBLOBs(QmcMsgsExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    List<QmcMsgs> selectByExample(QmcMsgsExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    int updateByExampleSelective(@Param("record") QmcMsgs record, @Param("example") QmcMsgsExample example);

    /**
     * 批量条件修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    int updateByExampleWithBLOBs(@Param("record") QmcMsgs record, @Param("example") QmcMsgsExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    int updateByExample(@Param("record") QmcMsgs record, @Param("example") QmcMsgsExample example);

    /**
     * 物理分页条件查询,支持大字段
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    List<QmcMsgs> selectByExampleWithBLOBsAndPage(QmcMsgsExample example, RowBounds rowBound);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    List<QmcMsgs> selectByExampleAndPage(QmcMsgsExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    int deleteByPrimaryKey(Long qmcMid);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    int insert(QmcMsgs record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    int insertSelective(QmcMsgs record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    QmcMsgs selectByPrimaryKey(Long qmcMid);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    int updateByPrimaryKeySelective(QmcMsgs record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    int updateByPrimaryKeyWithBLOBs(QmcMsgs record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2017-04-18 13:53:48
     */
    int updateByPrimaryKey(QmcMsgs record);
}