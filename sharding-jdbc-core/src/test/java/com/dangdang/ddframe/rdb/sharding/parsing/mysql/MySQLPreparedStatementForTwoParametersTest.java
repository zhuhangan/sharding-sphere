/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.parsing.mysql;

import com.dangdang.ddframe.rdb.sharding.api.fixture.ShardingRuleMockBuilder;
import com.dangdang.ddframe.rdb.sharding.constant.DatabaseType;
import com.dangdang.ddframe.rdb.sharding.parsing.AbstractBaseParseTest;
import com.dangdang.ddframe.rdb.sharding.parsing.SQLParsingEngine;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.context.condition.Conditions;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.context.table.Tables;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.statement.SQLStatement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;

@RunWith(Parameterized.class)
public final class MySQLPreparedStatementForTwoParametersTest extends AbstractBaseParseTest {
    
    public MySQLPreparedStatementForTwoParametersTest(
            final String testCaseName, final String sql, final String expectedSQL,
            final Tables expectedTables, final Conditions expectedConditions, final SQLStatement expectedSQLStatement) {
        super(testCaseName, sql, expectedSQL, expectedTables, expectedConditions, expectedSQLStatement);
    }
    
    @Parameters(name = "{0}")
    public static Collection<Object[]> dataParameters() {
        return AbstractBaseParseTest.dataParameters("com/dangdang/ddframe/rdb/sharding/parsing/mysql/prepared_statement/two_params/");
    }
    
    @Test
    public void assertParse() {
        assertSQLStatement(new SQLParsingEngine(DatabaseType.MySQL, getSql(), new ShardingRuleMockBuilder().addShardingColumns("user_id").addShardingColumns("order_id").addShardingColumns("state")
                .addGenerateKeyColumn("order", "order_id").build()).parse());
    }
}
