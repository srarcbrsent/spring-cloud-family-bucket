package com.ysu.zyw.tc.demo.svc;

import java.util.List;
import com.ysu.zyw.tc.demo.dao.jooq.tables.pojos.TUiClass;
import com.ysu.zyw.tc.demo.web.terms.UIClassTerms;

public interface UIClassService {

    Integer insert(TUiClass uiClass);

    Integer delete(Long pkClass);

    Integer update(TUiClass uiClass);

    TUiClass find(Long pkClass);

    List<TUiClass> list(UIClassTerms uIClassTerms);

    Long count(UIClassTerms uIClassTerms);
}
