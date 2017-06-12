package com.ysu.zyw.tc.demo.svc.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ysu.zyw.tc.demo.web.terms.UIPersonTerms;
import com.ysu.zyw.tc.demo.svc.UIPersonService;
import com.ysu.zyw.tc.demo.dao.jooq.tables.pojos.TUiPerson;

@Service()
public class UIPersonServiceImpl implements UIPersonService {

    @Override()
    public Integer insert(TUiPerson uiPerson) {
        return null;
    }

    @Override()
    public Integer delete(Long pkClass, Long pkPerson) {
        return null;
    }

    @Override()
    public Integer update(TUiPerson uiPerson) {
        return null;
    }

    @Override()
    public TUiPerson find(Long pkClass, Long pkPerson) {
        return null;
    }

    @Override()
    public List<TUiPerson> list(UIPersonTerms uIPersonTerms) {
        return null;
    }

    @Override()
    public Long count(UIPersonTerms uIPersonTerms) {
        return null;
    }
}
