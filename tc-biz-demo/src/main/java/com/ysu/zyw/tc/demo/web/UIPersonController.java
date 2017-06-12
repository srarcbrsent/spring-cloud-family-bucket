package com.ysu.zyw.tc.demo.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.ysu.zyw.tc.demo.web.terms.UIPersonTerms;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.ysu.zyw.tc.demo.svc.UIPersonService;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.ysu.zyw.tc.demo.dao.jooq.tables.pojos.TUiPerson;

@Controller()
@RequestMapping(value = "/v1/ui_person")
public class UIPersonController {

    private @Resource() UIPersonService uIPersonService;

    @PostMapping(value = "")
    public Integer insert(@RequestBody() TUiPerson uiPerson) {
        return uIPersonService.insert(uiPerson);
    }

    @DeleteMapping(value = "/{pkClass}/{pkPerson}")
    public Integer delete(@PathVariable(value = "pkClass") Long pkClass, @PathVariable(value = "pkPerson") Long pkPerson) {
        return uIPersonService.delete(pkClass, pkPerson);
    }

    @PutMapping(value = "")
    public Integer update(@RequestBody() TUiPerson uiPerson) {
        return uIPersonService.update(uiPerson);
    }

    @GetMapping(value = "/{pkClass}/{pkPerson}")
    public TUiPerson find(@PathVariable(value = "pkClass") Long pkClass, @PathVariable(value = "pkPerson") Long pkPerson) {
        return uIPersonService.find(pkClass, pkPerson);
    }

    @GetMapping(value = "/list")
    public List<TUiPerson> list(UIPersonTerms uIPersonTerms) {
        return uIPersonService.list(uIPersonTerms);
    }

    @GetMapping(value = "/count")
    public Long count(UIPersonTerms uIPersonTerms) {
        return uIPersonService.count(uIPersonTerms);
    }
}
