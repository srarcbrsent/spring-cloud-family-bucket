package com.ysu.zyw.tc.demo.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import com.ysu.zyw.tc.demo.dao.jooq.tables.pojos.TUiClass;
import org.springframework.cloud.netflix.feign.FeignClient;
import com.ysu.zyw.tc.demo.web.terms.UIClassTerms;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "demo-client")
public interface UIClassService {

    @RequestMapping(value = "/v1/ui_class", method = RequestMethod.POST)
    Integer insert(@RequestBody() TUiClass uiClass);

    @RequestMapping(value = "/v1/ui_class/{pkClass}", method = RequestMethod.DELETE)
    Integer delete(@PathVariable(value = "pkClass") Long pkClass);

    @RequestMapping(value = "/v1/ui_class", method = RequestMethod.PUT)
    Integer update(@RequestBody() TUiClass uiClass);

    @RequestMapping(value = "/v1/ui_class/{pkClass}", method = RequestMethod.GET)
    TUiClass find(@PathVariable(value = "pkClass") Long pkClass);

    @RequestMapping(value = "/v1/ui_class/list", method = RequestMethod.GET)
    List<TUiClass> list(UIClassTerms uIClassTerms);

    @RequestMapping(value = "/v1/ui_class/count", method = RequestMethod.GET)
    Long count(UIClassTerms uIClassTerms);
}
