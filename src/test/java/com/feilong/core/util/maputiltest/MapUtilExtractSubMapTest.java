/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.core.util.maputiltest;

import static java.util.Collections.emptyMap;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.feilong.core.util.MapUtil;
import com.feilong.test.User;

public class MapUtilExtractSubMapTest{

    /**
     * Test extract sub map 3.
     */
    @Test
    public void testExtractSubMap3(){
        Map<Long, User> map = new LinkedHashMap<Long, User>();
        map.put(1L, new User(100L));
        map.put(2L, new User(200L));
        map.put(5L, new User(500L));
        map.put(4L, new User(400L));

        Map<Long, Long> extractSubMap = MapUtil.extractSubMap(map, "id");
        assertThat(extractSubMap, allOf(hasEntry(1L, 100L), hasEntry(2L, 200L), hasEntry(5L, 500L), hasEntry(4L, 400L)));
    }

    /**
     * Test extract sub map.
     */
    //*********************************************************************************************
    @Test
    public void testExtractSubMapNullMap(){
        assertEquals(emptyMap(), MapUtil.extractSubMap(null, "id"));
    }

    @Test
    public void testExtractSubMapEmptyMap(){
        assertEquals(emptyMap(), MapUtil.extractSubMap(new HashMap<>(), "id"));
    }

    @Test(expected = NullPointerException.class)
    public void testExtractSubMapNullExtractPropertyName(){
        Map<Long, User> map = new LinkedHashMap<Long, User>();
        map.put(1L, new User(1L));
        map.put(2L, new User(2L));
        MapUtil.extractSubMap(map, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExtractSubMapEmptyExtractPropertyName(){
        Map<Long, User> map = new LinkedHashMap<Long, User>();
        map.put(1L, new User(1L));
        map.put(2L, new User(2L));
        MapUtil.extractSubMap(map, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExtractSubMapEmptyExtractPropertyName1(){
        Map<Long, User> map = new LinkedHashMap<Long, User>();
        map.put(1L, new User(1L));
        map.put(2L, new User(2L));
        MapUtil.extractSubMap(map, " ");
    }

}
