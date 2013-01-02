/**
 * Copyright 2013 55 Minutes (http://www.55minutes.com)
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
package fiftyfive.wicket.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.wicket.util.io.IOUtils;

public abstract class BaseValidatorTest
{
    protected abstract AbstractDocumentValidator createValidator();
    
    protected AbstractDocumentValidator validator(String resource)
        throws IOException
    {
        InputStream in = null;
        try
        {
            in = getClass().getResourceAsStream(resource);
            String doc = IOUtils.toString(in, "UTF-8");
            AbstractDocumentValidator validator = createValidator();
            validator.parse(doc);
            return validator;
        }
        finally
        {
            IOUtils.closeQuietly(in);
        }
    }
}
