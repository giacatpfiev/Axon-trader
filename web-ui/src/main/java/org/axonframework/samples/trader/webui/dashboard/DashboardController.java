/*
 * Copyright (c) 2010-2012. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.samples.trader.webui.dashboard;

import org.axonframework.samples.trader.query.portfolio.PortfolioView;
import org.axonframework.samples.trader.query.portfolio.PortfolioViewRepository;
import org.axonframework.samples.trader.query.transaction.TransactionView;
import org.axonframework.samples.trader.query.transaction.TransactionViewRepository;
import org.axonframework.samples.trader.webui.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Jettro Coenradie
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final static Logger logger = LoggerFactory.getLogger(DashboardController.class);
    private PortfolioViewRepository portfolioRepository;
    private TransactionViewRepository transactionRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model) {
        String identifier = SecurityUtil.obtainLoggedinUserIdentifier();
        logger.debug("Requested to obtain the portfolio for the user: {}", identifier);

        PortfolioView portfolio = portfolioRepository.findByUserIdentifier(identifier);
        if (portfolio == null) {
            throw new RuntimeException("You most certainly changed the id of the current logged in user " +
                                               "and the user did not logout.");
        }
        model.addAttribute("portfolio", portfolio);

        List<TransactionView> transactions = transactionRepository.findByPortfolioId(portfolio
                                                                                                      .getIdentifier());
        model.addAttribute("transactions", transactions);
        return "dashboard/index";
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void setPortfolioRepository(PortfolioViewRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void setTransactionRepository(TransactionViewRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
}
