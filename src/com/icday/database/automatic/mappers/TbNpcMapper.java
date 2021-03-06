package com.icday.database.automatic.mappers;

import com.icday.database.automatic.entitys.TbNpc;
import com.icday.database.automatic.entitys.TbNpcExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbNpcMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_npc
     *
     * @mbggenerated
     */
    int countByExample(TbNpcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_npc
     *
     * @mbggenerated
     */
    int deleteByExample(TbNpcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_npc
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String insid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_npc
     *
     * @mbggenerated
     */
    int insert(TbNpc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_npc
     *
     * @mbggenerated
     */
    int insertSelective(TbNpc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_npc
     *
     * @mbggenerated
     */
    List<TbNpc> selectByExample(TbNpcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_npc
     *
     * @mbggenerated
     */
    TbNpc selectByPrimaryKey(String insid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_npc
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TbNpc record, @Param("example") TbNpcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_npc
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TbNpc record, @Param("example") TbNpcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_npc
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TbNpc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_npc
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbNpc record);
}