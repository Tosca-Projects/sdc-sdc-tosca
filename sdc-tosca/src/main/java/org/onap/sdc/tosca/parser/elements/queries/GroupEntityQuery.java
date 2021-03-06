/*-
 * ============LICENSE_START=======================================================
 * sdc-tosca
 * ================================================================================
 * Copyright (C) 2017 - 2019 AT&T Intellectual Property. All rights reserved.
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

package org.onap.sdc.tosca.parser.elements.queries;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.onap.sdc.tosca.parser.api.IEntityDetails;
import org.onap.sdc.tosca.parser.elements.EntityDetailsFactory;
import org.onap.sdc.tosca.parser.enums.EntityTemplateType;
import org.onap.sdc.toscaparser.api.Group;
import org.onap.sdc.toscaparser.api.NodeTemplate;
import org.onap.sdc.toscaparser.api.SubstitutionMappings;
import org.onap.sdc.toscaparser.api.ToscaTemplate;

/**
 * Implements EntityQuery object for Groups
 */
public class GroupEntityQuery extends EntityQuery {

    private static final String VF_MODULE_UUID = "vfModuleModelUUID";
    private static final String VF_MODULE_CUSTOMIZATION_UUID = "vfModuleModelCustomizationUUID";

    GroupEntityQuery() {
        super(EntityTemplateType.GROUP, null, null);
    }

    GroupEntityQuery(String toscaType) {
        super(EntityTemplateType.GROUP, null, toscaType);
    }

    static List<IEntityDetails> convertGroupLisToEntityDetailsList(final Stream<Group> groups) {
        return groups.map(gr -> EntityDetailsFactory.createEntityDetails(EntityTemplateType.GROUP, gr))
            .collect(Collectors.toList());
    }

    @Override
    public List<IEntityDetails> getEntitiesFromTopologyTemplate(final NodeTemplate nodeTemplate) {
        final SubstitutionMappings subMappingToscaTemplate = nodeTemplate.getSubMappingToscaTemplate();
        if (subMappingToscaTemplate != null) {
            final List<Group> groups = subMappingToscaTemplate.getGroups();
            if (groups != null) {
                return convertGroupLisToEntityDetailsList(filter(groups));
            }
        }
        return Lists.newArrayList();
    }

    @Override
    public List<IEntityDetails> getEntitiesFromService(final ToscaTemplate toscaTemplate) {
        final List<Group> groups = toscaTemplate.getGroups();
        if (groups != null) {
            return convertGroupLisToEntityDetailsList(filter(groups));
        } else {
            return new ArrayList<>();
        }
    }

    private Stream<Group> filter(final List<Group> groupList) {
        return groupList.stream()
            .filter(gr -> isSearchCriteriaMatched(gr.getMetadata(), gr.getType()) ||
                isSearchCriteriaMatched(gr.getMetadata(), gr.getType(), VF_MODULE_UUID, VF_MODULE_CUSTOMIZATION_UUID));
    }

}
