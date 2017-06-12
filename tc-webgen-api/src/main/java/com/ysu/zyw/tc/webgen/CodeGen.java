package com.ysu.zyw.tc.webgen;

import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.gen.client.ClientGen;
import com.ysu.zyw.tc.webgen.gen.terms.TermsGen;
import com.ysu.zyw.tc.webgen.gen.svc.SvcGen;
import com.ysu.zyw.tc.webgen.gen.web.WebGen;


public class CodeGen {

    public static void gen(Config config) {
        config.getTableDetails().forEach(tableDetail -> {
            // gen terms
            TermsGen.generateQueryTerms(config, tableDetail);
            // gen client
            ClientGen.generateClient(config, tableDetail);
            // gen web
            WebGen.generateController(config, tableDetail);
            // gen svc
            SvcGen.generateService(config, tableDetail);
            // gen svc impl
            SvcGen.generateServiceImpl(config, tableDetail);
            // gen dao
            // gen dao impl
        });
    }

}
