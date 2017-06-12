package com.ysu.zyw.tc.demo.svc;

import java.util.List;
import com.ysu.zyw.tc.demo.web.terms.UIPersonTerms;
import com.ysu.zyw.tc.demo.dao.jooq.tables.pojos.TUiPerson;

public interface UIPersonService {

    Integer insert(TUiPerson uiPerson);

    Integer delete(Long pkClass, Long pkPerson);

    Integer update(TUiPerson uiPerson);

    TUiPerson find(Long pkClass, Long pkPerson);

    List<TUiPerson> list(UIPersonTerms uIPersonTerms);

    Long count(UIPersonTerms uIPersonTerms);
}
