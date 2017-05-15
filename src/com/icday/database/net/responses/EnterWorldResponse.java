package com.icday.database.net.responses;

import com.icday.net.decodes.ServerResponse;
import com.icday.database.net.datas.RoleData;
import java.util.ArrayList;
import com.icday.database.net.datas.PackData;
/**
 * 此类由protocol_generate_util自动生成
 */
public class EnterWorldResponse  extends ServerResponse{
	public EnterWorldResponse(int status , RoleData role, ArrayList<PackData> packs) throws Exception{
		super(0x30001 , status);
		output.writeAMFObject(role == null ? new Object() : role);
		output.writeAMFObject(packs == null ? new ArrayList<>() : packs);

	}


}