/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.oncrpc;

import org.junit.Test;

import junit.framework.Assert;

public class TestXDR {
  private void serializeInt(int times) {
    XDR w = new XDR();
    for (int i = 0; i < times; ++i)
      w.writeInt(23);

    XDR r = w.asReadOnlyWrap();
    for (int i = 0; i < times; ++i)
      Assert.assertEquals(r.readInt(), 23);
  }

  private void serializeLong(int times) {
    XDR w = new XDR();
    for (int i = 0; i < times; ++i)
      w.writeLongAsHyper(23);

    XDR r = w.asReadOnlyWrap();
    for (int i = 0; i < times; ++i)
      Assert.assertEquals(r.readHyper(), 23);
  }

  @Test
  public void testPerformance() {
    final int TEST_TIMES = 8 << 20;
    serializeInt(TEST_TIMES);
    serializeLong(TEST_TIMES);
  }
}
