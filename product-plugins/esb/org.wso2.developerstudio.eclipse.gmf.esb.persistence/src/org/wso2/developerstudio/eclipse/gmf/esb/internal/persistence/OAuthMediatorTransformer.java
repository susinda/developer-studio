package org.wso2.developerstudio.eclipse.gmf.esb.internal.persistence;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.synapse.endpoints.Endpoint;
import org.apache.synapse.mediators.base.SequenceMediator;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbNode;
import org.wso2.developerstudio.eclipse.gmf.esb.OAuthMediator;
import org.wso2.developerstudio.eclipse.gmf.esb.persistence.TransformationInfo;
import org.wso2.developerstudio.eclipse.gmf.esb.persistence.TransformerException;

public class OAuthMediatorTransformer extends AbstractEsbNodeTransformer  {

	public void transform(TransformationInfo information, EsbNode subject)
			throws TransformerException {
		// TODO Auto-generated method stub
		information.getParentSequence().addChild(createOAuthMediator(subject));
		// Transform the OAuth mediator output data flow path.
		doTransform(information,
				((OAuthMediator) subject).getOutputConnector());
		
	}

	public void createSynapseObject(TransformationInfo info, EObject subject,
			List<Endpoint> endPoints) {
		// TODO Auto-generated method stub
		
	}

	public void transformWithinSequence(TransformationInfo information,
			EsbNode subject, SequenceMediator sequence) throws TransformerException {
		// TODO Auto-generated method stub
		sequence.addChild(createOAuthMediator(subject));
		doTransformWithinSequence(information,((OAuthMediator) subject).getOutputConnector().getOutgoingLink(),sequence);
		
		
	}
	
	private org.wso2.carbon.identity.oauth.mediator.OAuthMediator createOAuthMediator(EsbNode subject){
		// Check subject.
		Assert.isTrue(subject instanceof OAuthMediator, "Invalid subject."); //$NON-NLS-1$
		OAuthMediator visualOauth = (OAuthMediator) subject;

		// Configure properties of the mediator.
		org.wso2.carbon.identity.oauth.mediator.OAuthMediator OauthMediator = new org.wso2.carbon.identity.oauth.mediator.OAuthMediator();
		setCommonProperties(OauthMediator, visualOauth);
		{
			if (StringUtils.isNotBlank(visualOauth.getRemoteServiceUrl())) {
				OauthMediator.setRemoteServiceUrl(visualOauth.getRemoteServiceUrl());
			} else {
				throw new IllegalArgumentException(
						Messages.OAuthMediatorTransformer_Remote_Server_Url_Required_Error_Message);
			}
			
			if (StringUtils.isNotBlank(visualOauth.getUsername())) {
				OauthMediator.setUsername(visualOauth.getUsername());
			} else {
				throw new IllegalArgumentException(
						Messages.OAuthMediatorTransformer_Username_Required_Error_Message);
			}
			
			if (StringUtils.isNotBlank(visualOauth.getPassword())) {
				OauthMediator.setPassword(visualOauth.getPassword());
			} else {
				throw new IllegalArgumentException(
						Messages.OAuthMediatorTransformer_Password_Required_Error_Message);
			}
		}
		return OauthMediator;
	}

}
