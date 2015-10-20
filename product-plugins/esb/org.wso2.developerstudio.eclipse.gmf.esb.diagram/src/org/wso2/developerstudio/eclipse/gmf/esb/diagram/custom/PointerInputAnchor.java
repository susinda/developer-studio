package org.wso2.developerstudio.eclipse.gmf.esb.diagram.custom;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;

public class PointerInputAnchor extends AbstractConnectionAnchor {
	public PointerInputAnchor(AbstractPointerShape pointerShape) {
		super(pointerShape);
	}

	/**
	 * {@inheritDoc}
	 */
	public Point getLocation(Point reference) {
		Point result = ((AbstractPointerShape) getOwner()).getInputAnchorPoint();
		getOwner().translateToAbsolute(result);
		return result;
	}	
}
