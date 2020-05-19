import { Moment } from 'moment';

export interface IUser {
  id?: any;
  login?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  activated?: boolean;
  langKey?: string;
  authorities?: string[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  password?: string;
  departmentID?: number;
  dateOfBirth?: Moment;
  phoneNumber?: string;
  code?: string;
  vice?: string;
  fileBase64?: string;
}

export class User implements IUser {
  constructor(
    public id?: any,
    public login?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public activated?: boolean,
    public langKey?: string,
    public authorities?: string[],
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public password?: string,
    public departmentID?: number,
    public dateOfBirth?: Moment,
    public phoneNumber?: string,
    public code?: string,
    public vice?: string,
    public fileBase64?: string
  ) {}
}
