package com.randomnoun.maven.doxia.module.html;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.doxia.module.site.AbstractSiteModule;
import org.apache.maven.doxia.module.site.SiteModule;
import org.codehaus.plexus.component.annotations.Component;

/**
 * {@link org.apache.maven.doxia.module.site.SiteModule} for HTML.
 *
 * @author knoxg
 */
@Component( role = SiteModule.class, hint = "html" )
public class HtmlSiteModule
    extends AbstractSiteModule
{

    /**
     * The source directory for HTML files.
     */
    public static final String SOURCE_DIRECTORY = "html";

    /**
     * The extension for HTML files.
     */
    public static final String FILE_EXTENSION = "html";

    /**
     * Build a new instance of {@link MarkdownSiteModule}.
     */
    public HtmlSiteModule()
    {
        super( SOURCE_DIRECTORY, FILE_EXTENSION, HtmlParser.ROLE_HINT );
    }
}

