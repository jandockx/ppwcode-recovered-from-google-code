﻿#pragma warning disable 1591
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.296
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Create_CRUD_Procedures
{
	using System.Data.Linq;
	using System.Data.Linq.Mapping;
	using System.Data;
	using System.Collections.Generic;
	using System.Reflection;
	using System.Linq;
	using System.Linq.Expressions;
	using System.ComponentModel;
	using System;
	
	
	[global::System.Data.Linq.Mapping.DatabaseAttribute(Name="zetes_registration")]
	public partial class InformationSchemaClassesDataContext : System.Data.Linq.DataContext
	{
		
		private static System.Data.Linq.Mapping.MappingSource mappingSource = new AttributeMappingSource();
		
    #region Extensibility Method Definitions
    partial void OnCreated();
    partial void InsertPermission(Permission instance);
    partial void UpdatePermission(Permission instance);
    partial void DeletePermission(Permission instance);
    #endregion
		
		public InformationSchemaClassesDataContext() : 
				base(global::Create_CRUD_Procedures.Properties.Settings.Default.zetesConnectionString, mappingSource)
		{
			OnCreated();
		}
		
		public InformationSchemaClassesDataContext(string connection) : 
				base(connection, mappingSource)
		{
			OnCreated();
		}
		
		public InformationSchemaClassesDataContext(System.Data.IDbConnection connection) : 
				base(connection, mappingSource)
		{
			OnCreated();
		}
		
		public InformationSchemaClassesDataContext(string connection, System.Data.Linq.Mapping.MappingSource mappingSource) : 
				base(connection, mappingSource)
		{
			OnCreated();
		}
		
		public InformationSchemaClassesDataContext(System.Data.IDbConnection connection, System.Data.Linq.Mapping.MappingSource mappingSource) : 
				base(connection, mappingSource)
		{
			OnCreated();
		}
		
		public System.Data.Linq.Table<RelationInfo> RelationInfos
		{
			get
			{
				return this.GetTable<RelationInfo>();
			}
		}
		
		public System.Data.Linq.Table<Permission> Permissions
		{
			get
			{
				return this.GetTable<Permission>();
			}
		}
	}
	
	[global::System.Data.Linq.Mapping.TableAttribute(Name="dbo.v_sys_relation_info")]
	public partial class RelationInfo
	{
		
		private string _TABLE_NAME;
		
		private string _TABLE_TYPE;
		
		private string _COLUMN_NAME;
		
		private System.Nullable<int> _ORDINAL_POSITION;
		
		private string _DATA_TYPE;
		
		private System.Nullable<int> _CHARACTER_OCTET_LENGTH;
		
		private System.Nullable<int> _CHARACTER_MAXIMUM_LENGTH;
		
		private System.Nullable<byte> _NUMERIC_PRECISION;
		
		private System.Nullable<int> _NUMERIC_SCALE;
		
		private bool _is_computed;
		
		private bool _is_identity;
		
		private System.Nullable<bool> _is_pk;
		
		private System.Nullable<int> _PK_ORDINAL_POSITION;
		
		private System.Nullable<bool> _has_audit;
		
		public RelationInfo()
		{
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_TABLE_NAME", DbType="NVarChar(128) NOT NULL", CanBeNull=false)]
		public string TABLE_NAME
		{
			get
			{
				return this._TABLE_NAME;
			}
			set
			{
				if ((this._TABLE_NAME != value))
				{
					this._TABLE_NAME = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_TABLE_TYPE", DbType="VarChar(10)")]
		public string TABLE_TYPE
		{
			get
			{
				return this._TABLE_TYPE;
			}
			set
			{
				if ((this._TABLE_TYPE != value))
				{
					this._TABLE_TYPE = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_COLUMN_NAME", DbType="NVarChar(128)")]
		public string COLUMN_NAME
		{
			get
			{
				return this._COLUMN_NAME;
			}
			set
			{
				if ((this._COLUMN_NAME != value))
				{
					this._COLUMN_NAME = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_ORDINAL_POSITION", DbType="Int")]
		public System.Nullable<int> ORDINAL_POSITION
		{
			get
			{
				return this._ORDINAL_POSITION;
			}
			set
			{
				if ((this._ORDINAL_POSITION != value))
				{
					this._ORDINAL_POSITION = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_DATA_TYPE", DbType="NVarChar(128)")]
		public string DATA_TYPE
		{
			get
			{
				return this._DATA_TYPE;
			}
			set
			{
				if ((this._DATA_TYPE != value))
				{
					this._DATA_TYPE = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_CHARACTER_OCTET_LENGTH", DbType="Int")]
		public System.Nullable<int> CHARACTER_OCTET_LENGTH
		{
			get
			{
				return this._CHARACTER_OCTET_LENGTH;
			}
			set
			{
				if ((this._CHARACTER_OCTET_LENGTH != value))
				{
					this._CHARACTER_OCTET_LENGTH = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_CHARACTER_MAXIMUM_LENGTH", DbType="Int")]
		public System.Nullable<int> CHARACTER_MAXIMUM_LENGTH
		{
			get
			{
				return this._CHARACTER_MAXIMUM_LENGTH;
			}
			set
			{
				if ((this._CHARACTER_MAXIMUM_LENGTH != value))
				{
					this._CHARACTER_MAXIMUM_LENGTH = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_NUMERIC_PRECISION", DbType="TinyInt")]
		public System.Nullable<byte> NUMERIC_PRECISION
		{
			get
			{
				return this._NUMERIC_PRECISION;
			}
			set
			{
				if ((this._NUMERIC_PRECISION != value))
				{
					this._NUMERIC_PRECISION = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_NUMERIC_SCALE", DbType="Int")]
		public System.Nullable<int> NUMERIC_SCALE
		{
			get
			{
				return this._NUMERIC_SCALE;
			}
			set
			{
				if ((this._NUMERIC_SCALE != value))
				{
					this._NUMERIC_SCALE = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_is_computed", DbType="Bit NOT NULL")]
		public bool is_computed
		{
			get
			{
				return this._is_computed;
			}
			set
			{
				if ((this._is_computed != value))
				{
					this._is_computed = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_is_identity", DbType="Bit NOT NULL")]
		public bool is_identity
		{
			get
			{
				return this._is_identity;
			}
			set
			{
				if ((this._is_identity != value))
				{
					this._is_identity = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_is_pk", DbType="Bit")]
		public System.Nullable<bool> is_pk
		{
			get
			{
				return this._is_pk;
			}
			set
			{
				if ((this._is_pk != value))
				{
					this._is_pk = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_PK_ORDINAL_POSITION", DbType="Int")]
		public System.Nullable<int> PK_ORDINAL_POSITION
		{
			get
			{
				return this._PK_ORDINAL_POSITION;
			}
			set
			{
				if ((this._PK_ORDINAL_POSITION != value))
				{
					this._PK_ORDINAL_POSITION = value;
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_has_audit", DbType="Bit")]
		public System.Nullable<bool> has_audit
		{
			get
			{
				return this._has_audit;
			}
			set
			{
				if ((this._has_audit != value))
				{
					this._has_audit = value;
				}
			}
		}
	}
	
	[global::System.Data.Linq.Mapping.TableAttribute(Name="dbo.Permission")]
	public partial class Permission : INotifyPropertyChanging, INotifyPropertyChanged
	{
		
		private static PropertyChangingEventArgs emptyChangingEventArgs = new PropertyChangingEventArgs(String.Empty);
		
		private int _PermissionId;
		
		private System.Nullable<int> _RequiredPermissionId;
		
		private char _Action;
		
		private string _Name;
		
		private System.Xml.Linq.XElement _Description;
		
		private System.Nullable<int> _UserCreate;
		
		private System.Nullable<System.DateTime> _DateCreated;
		
		private System.Nullable<int> _UserModified;
		
		private System.Nullable<System.DateTime> _DateModified;
		
		private EntitySet<Permission> _Permissions;
		
		private EntityRef<Permission> _Permission1;
		
    #region Extensibility Method Definitions
    partial void OnLoaded();
    partial void OnValidate(System.Data.Linq.ChangeAction action);
    partial void OnCreated();
    partial void OnPermissionIdChanging(int value);
    partial void OnPermissionIdChanged();
    partial void OnRequiredPermissionIdChanging(System.Nullable<int> value);
    partial void OnRequiredPermissionIdChanged();
    partial void OnActionChanging(char value);
    partial void OnActionChanged();
    partial void OnNameChanging(string value);
    partial void OnNameChanged();
    partial void OnDescriptionChanging(System.Xml.Linq.XElement value);
    partial void OnDescriptionChanged();
    partial void OnUserCreateChanging(System.Nullable<int> value);
    partial void OnUserCreateChanged();
    partial void OnDateCreatedChanging(System.Nullable<System.DateTime> value);
    partial void OnDateCreatedChanged();
    partial void OnUserModifiedChanging(System.Nullable<int> value);
    partial void OnUserModifiedChanged();
    partial void OnDateModifiedChanging(System.Nullable<System.DateTime> value);
    partial void OnDateModifiedChanged();
    #endregion
		
		public Permission()
		{
			this._Permissions = new EntitySet<Permission>(new Action<Permission>(this.attach_Permissions), new Action<Permission>(this.detach_Permissions));
			this._Permission1 = default(EntityRef<Permission>);
			OnCreated();
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_PermissionId", DbType="Int NOT NULL", IsPrimaryKey=true)]
		public int PermissionId
		{
			get
			{
				return this._PermissionId;
			}
			set
			{
				if ((this._PermissionId != value))
				{
					this.OnPermissionIdChanging(value);
					this.SendPropertyChanging();
					this._PermissionId = value;
					this.SendPropertyChanged("PermissionId");
					this.OnPermissionIdChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_RequiredPermissionId", DbType="Int")]
		public System.Nullable<int> RequiredPermissionId
		{
			get
			{
				return this._RequiredPermissionId;
			}
			set
			{
				if ((this._RequiredPermissionId != value))
				{
					if (this._Permission1.HasLoadedOrAssignedValue)
					{
						throw new System.Data.Linq.ForeignKeyReferenceAlreadyHasValueException();
					}
					this.OnRequiredPermissionIdChanging(value);
					this.SendPropertyChanging();
					this._RequiredPermissionId = value;
					this.SendPropertyChanged("RequiredPermissionId");
					this.OnRequiredPermissionIdChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_Action", DbType="Char(1) NOT NULL")]
		public char Action
		{
			get
			{
				return this._Action;
			}
			set
			{
				if ((this._Action != value))
				{
					this.OnActionChanging(value);
					this.SendPropertyChanging();
					this._Action = value;
					this.SendPropertyChanged("Action");
					this.OnActionChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_Name", DbType="NVarChar(128) NOT NULL", CanBeNull=false)]
		public string Name
		{
			get
			{
				return this._Name;
			}
			set
			{
				if ((this._Name != value))
				{
					this.OnNameChanging(value);
					this.SendPropertyChanging();
					this._Name = value;
					this.SendPropertyChanged("Name");
					this.OnNameChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_Description", DbType="Xml NOT NULL", CanBeNull=false, UpdateCheck=UpdateCheck.Never)]
		public System.Xml.Linq.XElement Description
		{
			get
			{
				return this._Description;
			}
			set
			{
				if ((this._Description != value))
				{
					this.OnDescriptionChanging(value);
					this.SendPropertyChanging();
					this._Description = value;
					this.SendPropertyChanged("Description");
					this.OnDescriptionChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_UserCreate", DbType="Int")]
		public System.Nullable<int> UserCreate
		{
			get
			{
				return this._UserCreate;
			}
			set
			{
				if ((this._UserCreate != value))
				{
					this.OnUserCreateChanging(value);
					this.SendPropertyChanging();
					this._UserCreate = value;
					this.SendPropertyChanged("UserCreate");
					this.OnUserCreateChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_DateCreated", DbType="DateTime")]
		public System.Nullable<System.DateTime> DateCreated
		{
			get
			{
				return this._DateCreated;
			}
			set
			{
				if ((this._DateCreated != value))
				{
					this.OnDateCreatedChanging(value);
					this.SendPropertyChanging();
					this._DateCreated = value;
					this.SendPropertyChanged("DateCreated");
					this.OnDateCreatedChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_UserModified", DbType="Int")]
		public System.Nullable<int> UserModified
		{
			get
			{
				return this._UserModified;
			}
			set
			{
				if ((this._UserModified != value))
				{
					this.OnUserModifiedChanging(value);
					this.SendPropertyChanging();
					this._UserModified = value;
					this.SendPropertyChanged("UserModified");
					this.OnUserModifiedChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_DateModified", DbType="DateTime")]
		public System.Nullable<System.DateTime> DateModified
		{
			get
			{
				return this._DateModified;
			}
			set
			{
				if ((this._DateModified != value))
				{
					this.OnDateModifiedChanging(value);
					this.SendPropertyChanging();
					this._DateModified = value;
					this.SendPropertyChanged("DateModified");
					this.OnDateModifiedChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.AssociationAttribute(Name="Permission_Permission", Storage="_Permissions", ThisKey="PermissionId", OtherKey="RequiredPermissionId")]
		public EntitySet<Permission> Permissions
		{
			get
			{
				return this._Permissions;
			}
			set
			{
				this._Permissions.Assign(value);
			}
		}
		
		[global::System.Data.Linq.Mapping.AssociationAttribute(Name="Permission_Permission", Storage="_Permission1", ThisKey="RequiredPermissionId", OtherKey="PermissionId", IsForeignKey=true)]
		public Permission Permission1
		{
			get
			{
				return this._Permission1.Entity;
			}
			set
			{
				Permission previousValue = this._Permission1.Entity;
				if (((previousValue != value) 
							|| (this._Permission1.HasLoadedOrAssignedValue == false)))
				{
					this.SendPropertyChanging();
					if ((previousValue != null))
					{
						this._Permission1.Entity = null;
						previousValue.Permissions.Remove(this);
					}
					this._Permission1.Entity = value;
					if ((value != null))
					{
						value.Permissions.Add(this);
						this._RequiredPermissionId = value.PermissionId;
					}
					else
					{
						this._RequiredPermissionId = default(Nullable<int>);
					}
					this.SendPropertyChanged("Permission1");
				}
			}
		}
		
		public event PropertyChangingEventHandler PropertyChanging;
		
		public event PropertyChangedEventHandler PropertyChanged;
		
		protected virtual void SendPropertyChanging()
		{
			if ((this.PropertyChanging != null))
			{
				this.PropertyChanging(this, emptyChangingEventArgs);
			}
		}
		
		protected virtual void SendPropertyChanged(String propertyName)
		{
			if ((this.PropertyChanged != null))
			{
				this.PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
			}
		}
		
		private void attach_Permissions(Permission entity)
		{
			this.SendPropertyChanging();
			entity.Permission1 = this;
		}
		
		private void detach_Permissions(Permission entity)
		{
			this.SendPropertyChanging();
			entity.Permission1 = null;
		}
	}
}
#pragma warning restore 1591
