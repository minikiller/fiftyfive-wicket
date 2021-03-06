/**
 * Copyright 2014 55 Minutes (http://www.55minutes.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fiftyfive.wicket.js.v3;

import fiftyfive.wicket.js.JavaScriptDependencySettings;
import fiftyfive.wicket.js.locator.SprocketsParserImplV3;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class IntegrationTest
{
    @Test
    public void testRender() throws Exception
    {
        WicketTester t = new WicketTester(new WebApplication() {
            @Override
            public Class<? extends WebPage> getHomePage()
            {
                return IntegrationTestPage.class;
            }
            @Override
            protected void init()
            {
                super.init();
                getResourceSettings().setCachingStrategy(
                    NoOpResourceCachingStrategy.INSTANCE
                );
                JavaScriptDependencySettings.get()
                    .addLibraryPath(IntegrationTest.class, "customlib")
                    .setSprocketsParser(new SprocketsParserImplV3());
            }
        });
        t.startPage(IntegrationTestPage.class);
        t.assertRenderedPage(IntegrationTestPage.class);
        t.assertResultPage(
            IntegrationTestPage.class,
            "IntegrationTestPage-expected.html"
        );
    }
}
