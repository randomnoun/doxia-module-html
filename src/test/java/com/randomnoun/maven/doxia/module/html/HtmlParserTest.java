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

import java.io.Reader;

import java.util.Iterator;

import org.apache.maven.doxia.parser.AbstractParserTest;
import org.apache.maven.doxia.parser.ParseException;
import org.apache.maven.doxia.parser.Parser;
import org.apache.maven.doxia.sink.SinkEventElement;
import org.apache.maven.doxia.sink.SinkEventTestingSink;

import org.codehaus.plexus.util.IOUtil;

/**
 * Tests for {@link HtmlParser}.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 1.3
 */
public class HtmlParserTest
    extends AbstractParserTest
{

    /**
     * The {@link HtmlParser} used for the tests.
     */
    protected HtmlParser parser;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();
        parser = (HtmlParser) lookup( Parser.ROLE, HtmlParser.ROLE_HINT );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Parser createParser()
    {
        return parser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String outputExtension()
    {
        return HtmlSiteModule.FILE_EXTENSION;
    }

    /**
     * Assert the paragraph sink event is fired when parsing "paragraph.html".
     *
     * @throws Exception if the event list is not correct when parsing the document.
     */
    public void testParagraphSinkEvent()
        throws Exception
    {
        Iterator<SinkEventElement> it = parseFileToEventTestingSink( "paragraph" ).getEventList().iterator();

        assertEquals( it, "body", "paragraph", "text", "paragraph_", "body_" );

        assertFalse( it.hasNext() );
    }

    /**
     * Parse the file and return a {@link SinkEventTestingSink}.
     *
     * @param file the file to parse with {@link #parser}.
     * @return a sink to test parsing events.
     * @throws ParseException if the document parsing failed.
     */
    protected SinkEventTestingSink parseFileToEventTestingSink( String file )
        throws ParseException
    {
        Reader reader = null;
        SinkEventTestingSink sink = null;
        try
        {
            reader = getTestReader( file );
            sink = new SinkEventTestingSink();
            parser.parse( reader, sink );
        }
        finally
        {
            IOUtil.close( reader );
        }

        return sink;
    }
}
