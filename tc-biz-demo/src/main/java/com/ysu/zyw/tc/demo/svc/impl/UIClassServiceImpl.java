package com.ysu.zyw.tc.demo.svc.impl;

import com.ysu.zyw.tc.demo.svc.UIClassService;
import java.util.List;
import com.ysu.zyw.tc.demo.dao.jooq.tables.pojos.TUiClass;
import org.springframework.stereotype.Service;
import com.ysu.zyw.tc.demo.web.terms.UIClassTerms;

@Service()
public class UIClassServiceImpl implements UIClassService {

    @Override()
    public Integer insert(TUiClass uiClass) {
        return null;
    }

    @Override()
    public Integer delete(Long pkClass) {
        return null;
    }

    @Override()
    public Integer update(TUiClass uiClass) {
        return null;
    }

    @Override()
    public TUiClass find(Long pkClass) {
        return null;
    }

    @Override()
    public List<TUiClass> list(UIClassTerms uIClassTerms) {
        return null;
    }

    @Override()
    public Long count(UIClassTerms uIClassTerms) {
        return null;
    }
}
