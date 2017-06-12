package com.ysu.zyw.tc.demo.web;

import com.ysu.zyw.tc.demo.svc.UIClassService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.ysu.zyw.tc.demo.dao.jooq.tables.pojos.TUiClass;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.ysu.zyw.tc.demo.web.terms.UIClassTerms;
import org.springframework.web.bind.annotation.DeleteMapping;

@Controller()
@RequestMapping(value = "/v1/ui_class")
public class UIClassController {

    private @Resource() UIClassService uIClassService;

    @PostMapping(value = "")
    public Integer insert(@RequestBody() TUiClass uiClass) {
        return uIClassService.insert(uiClass);
    }

    @DeleteMapping(value = "/{pkClass}")
    public Integer delete(@PathVariable(value = "pkClass") Long pkClass) {
        return uIClassService.delete(pkClass);
    }

    @PutMapping(value = "")
    public Integer update(@RequestBody() TUiClass uiClass) {
        return uIClassService.update(uiClass);
    }

    @GetMapping(value = "/{pkClass}")
    public TUiClass find(@PathVariable(value = "pkClass") Long pkClass) {
        return uIClassService.find(pkClass);
    }

    @GetMapping(value = "/list")
    public List<TUiClass> list(UIClassTerms uIClassTerms) {
        return uIClassService.list(uIClassTerms);
    }

    @GetMapping(value = "/count")
    public Long count(UIClassTerms uIClassTerms) {
        return uIClassService.count(uIClassTerms);
    }
}
