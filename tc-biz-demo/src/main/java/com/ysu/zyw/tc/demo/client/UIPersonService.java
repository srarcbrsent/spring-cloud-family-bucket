package com.ysu.zyw.tc.demo.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import com.ysu.zyw.tc.demo.web.terms.UIPersonTerms;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ysu.zyw.tc.demo.dao.jooq.tables.pojos.TUiPerson;

@FeignClient(value = "demo-client")
public interface UIPersonService {

    @RequestMapping(value = "/v1/ui_person", method = RequestMethod.POST)
    Integer insert(@RequestBody() TUiPerson uiPerson);

    @RequestMapping(value = "/v1/ui_person/{pkClass}/{pkPerson}", method = RequestMethod.DELETE)
    Integer delete(@PathVariable(value = "pkClass") Long pkClass, @PathVariable(value = "pkPerson") Long pkPerson);

    @RequestMapping(value = "/v1/ui_person", method = RequestMethod.PUT)
    Integer update(@RequestBody() TUiPerson uiPerson);

    @RequestMapping(value = "/v1/ui_person/{pkClass}/{pkPerson}", method = RequestMethod.GET)
    TUiPerson find(@PathVariable(value = "pkClass") Long pkClass, @PathVariable(value = "pkPerson") Long pkPerson);

    @RequestMapping(value = "/v1/ui_person/list", method = RequestMethod.GET)
    List<TUiPerson> list(UIPersonTerms uIPersonTerms);

    @RequestMapping(value = "/v1/ui_person/count", method = RequestMethod.GET)
    Long count(UIPersonTerms uIPersonTerms);
}
