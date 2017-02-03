/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.cmu.oli.authz;

import edu.cmu.oli.authz.control.AuthzProvider;
import edu.cmu.oli.authz.domain.*;
import edu.cmu.oli.authz.logging.LoggerExposer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

@RunWith(Arquillian.class)
public class TemplateTestIT {
    @Deployment(testable = false)
    public static Archive<?> createTestArchive() {

        Collection<String> dependencies = Arrays.asList(
                "com.google.code.gson:gson:2.2.2"
        );

        File[] files = Maven.resolver()
                .loadPomFromFile("pom.xml").resolve(dependencies)
                //.importRuntimeDependencies()
               // .resolve()
                .withTransitivity()
                .asFile();
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Action.class, ActionEdge.class, Actor.class, ActorEdge.class,
                        Authorization.class, Item.class, ItemEdge.class, LoggerExposer.class,
                        AuthzProvider.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("import.sql", "import.sql")
                .addAsLibraries(files)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml", "test-ds.xml");
    }

    @Inject
    AuthzProvider authzProvider;

    @Inject
    Logger log;

    @Test
    public void should_confirm_authz_checkes_are_working() throws Exception {
        String authorized = authzProvider.isAuthorized("administrator", "delete", "authorizations");
        //log.log(Level.INFO, "The return value from is authorized call " +authorized);
        Assert.assertEquals(authorized, "true");
    }

    @Test
    public void should_test_find_all_per_entity_is_working() throws Exception {
        String authorized = authzProvider.process("authorization", null, null, "find");
        //log.log(Level.INFO, "The return value from find all call " +authorized);
        // Assert that authorized is not an empty json array
        Assert.assertNotSame(authorized, "[]");
    }

}
