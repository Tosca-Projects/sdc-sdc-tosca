package org.onap.sdc.tosca.parser.elements;

import org.onap.sdc.tosca.parser.api.IEntityDetails;
import org.onap.sdc.tosca.parser.enums.EntityTemplateType;
import org.onap.sdc.toscaparser.api.EntityTemplate;
import org.onap.sdc.toscaparser.api.Group;
import org.onap.sdc.toscaparser.api.NodeTemplate;
import org.onap.sdc.toscaparser.api.elements.Metadata;

import java.util.List;
import java.util.stream.Collectors;

public class GroupEntityDetails extends EntityDetails {
    private final Group group;

    GroupEntityDetails(EntityTemplate entityTemplate, NodeTemplate parentNode)  {
        super(entityTemplate,parentNode);
        group = (Group)getEntityTemplate();
    }

    @Override
    public EntityTemplateType getType() {
        return EntityTemplateType.GROUP;
    }

    @Override
    public List<IEntityDetails> getMemberNodes() {
        return group.getMemberNodes()
                .stream()
                .map(m->EntityDetailsFactory.createEntityDetails(EntityTemplateType.NODE_TEMPLATE, m, null))
                .collect(Collectors.toList());
    }

    @Override
    public Metadata getMetadata() {
        return group.getMetadata();
    }
}
