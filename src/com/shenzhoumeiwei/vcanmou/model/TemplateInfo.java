package com.shenzhoumeiwei.vcanmou.model;//

import java.util.List;
public class TemplateInfo extends Response {

	public List<Template> List;

	public String GetPosterPagesEntityList;
	public class Template {
		public PosterTemplatesEntity PosterTemplatesEntity;
		public PosterTypesEntityList PosterTypesEntityList;
	}

	public class PosterTemplatesEntity {
		public String PTM_ID;// 模板ID
		public String PTM_Title;//
		public String PTM_Code;//
		public String PTM_Description;//
		public String PTM_ViewCount;//
		public String PTM_IssueCount;//
		public String PTM_Remark;//
		public String PTM_KeyWord;//
		public String PTM_Image;//
		public String PTM_FloatImage;//,
		public String PTM_Order;//
		public String LastUpdateTime;//
		public String SetTime;//
		public String Reamrk;//
		public String IsDelete;//
		public String SaveState;//
		
		
	}

	public class PosterTypesEntityList {
		public String PT_ID;//
		public String PT_Code;//
		public String PT_Name;//
		public String PT_ParentID;//
		public String PT_Order;//
		public String LastUpdateTime;//
		public String SetTime;//
		public String Remark;//
		public String IsDelete;//
		public String SaveState;//
	}
}
