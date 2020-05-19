import { Moment } from 'moment';

export class Account {
  constructor(
    public activated: boolean,
    public authorities: string[],
    public email: string,
    public firstName: string,
    public langKey: string,
    public lastName: string,
    public login: string,
    public imageUrl: string,
    public departmentID: number,
    public dateOfBirth: Moment,
    public phoneNumber: string,
    public code: string,
    public vice: string,
    public fileBase64: string
  ) {}
}
