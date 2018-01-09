package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.TabooVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/12/5.
 */
@Repository
public interface TabooRepository extends JpaRepository<TabooVO,Long> {

    public TabooVO getByCPkId(String pkid);

    @Modifying
    @Query(value = "update t_taboo set c_eff_mrk=?2 where c_pk_id=?1",nativeQuery = true)
    @Transactional
    public void updateEffMrk(String pkId,String effMrk);
}
