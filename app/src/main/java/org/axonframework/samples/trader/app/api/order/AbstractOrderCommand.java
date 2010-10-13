/*
 * Copyright (c) 2010. Gridshore
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

package org.axonframework.samples.trader.app.api.order;

import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.domain.AggregateIdentifierFactory;

/**
 * @author Allard Buijze
 */
public abstract class AbstractOrderCommand {

    private AggregateIdentifier userId;
    private AggregateIdentifier orderBookId;
    private long tradeCount;
    private int itemPrice;
    private AggregateIdentifier orderId;

    protected AbstractOrderCommand(AggregateIdentifier userId, AggregateIdentifier orderBookId, long tradeCount, int itemPrice) {
        this.userId = userId;
        this.orderBookId = orderBookId;
        this.tradeCount = tradeCount;
        this.itemPrice = itemPrice;
        this.orderId = AggregateIdentifierFactory.randomIdentifier();
    }

    public AggregateIdentifier getUserId() {
        return userId;
    }

    public AggregateIdentifier getOrderBookId() {
        return orderBookId;
    }

    public long getTradeCount() {
        return tradeCount;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public AggregateIdentifier getOrderId() {
        return orderId;
    }
}
