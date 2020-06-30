package aubo;

import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.property.Property;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.property.valuetypedef.PropertyValueTypeDef;

public class SensorSubModel extends SubModel{
	private Property joint1, joint2, joint3, joint4, joint5, joint6, x, y, z, rx, ry, rz;
	
	public SensorSubModel() {
		setIdShort("Sensor");
		joint1 = new Property();
		joint1.setIdShort("Joint 1");
		joint1.setValueType(PropertyValueTypeDef.Integer);
		addSubModelElement(joint1);
		
		joint2 = new Property();
		joint2.setIdShort("Joint 2");
		joint2.setValueType(PropertyValueTypeDef.Integer);	
		addSubModelElement(joint2);
		
		joint3 = new Property();
		joint3.setIdShort("Joint 3");
		joint3.setValueType(PropertyValueTypeDef.Integer);		
		addSubModelElement(joint3);
		
		joint4 = new Property();
		joint4.setIdShort("Joint 4");
		joint4.setValueType(PropertyValueTypeDef.Integer);	
		addSubModelElement(joint4);
		
		joint5 = new Property();
		joint5.setIdShort("Joint 5");
		joint5.setValueType(PropertyValueTypeDef.Integer);	
		addSubModelElement(joint5);
		
		joint6 = new Property();
		joint6.setIdShort("Joint 6");
		joint6.setValueType(PropertyValueTypeDef.Integer);	
		addSubModelElement(joint6);						                     
	}
	
	public void updatePosition(PositionValue currentvalue) {
        // Properties mit den neuen Daten updaten
		this.joint1.set(currentvalue.joint1);
		this.joint2.set(currentvalue.joint2);
		this.joint3.set(currentvalue.joint3);
		this.joint4.set(currentvalue.joint4);
		this.joint5.set(currentvalue.joint5);
		this.joint6.set(currentvalue.joint6);
    }
}
