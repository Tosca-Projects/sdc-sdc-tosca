/*-
 * ============LICENSE_START=======================================================
 * sdc-distribution-client
 * ================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
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
 * ============LICENSE_END=========================================================
 */

package org.openecomp.sdc.tosca.parser.impl;

import java.util.Arrays;
import java.util.List;

public enum SdcTypes {

    CP, VL, VF, VFC, PNF, SERVICE, CVFC, SERVICE_PROXY, CONFIGURATION;

    public static List<SdcTypes> complexTypes = Arrays.asList(VF, PNF, SERVICE, CVFC);

    public static boolean isComplex(SdcTypes sdcType) {
        return complexTypes.contains(sdcType);
    }
}
