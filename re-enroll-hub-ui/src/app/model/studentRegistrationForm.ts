export class StudentRegistrationForm {
  constructor(
    public email: string,
    public university: string,
    public password: string,
    public confirmPassword: string
  ) {}
}
