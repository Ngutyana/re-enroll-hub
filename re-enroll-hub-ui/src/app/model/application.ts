import {Attachment} from "./attachment";

export class Application {
  constructor(
    public facultyOfStudy: string,
    public qualification: string,
    public yearOfQualification: string,
    public yearOfDeregistration: number,
    public reasonOfDeregistration: string,
    public storyBehindDeregistration: string,
    public motivation: string,
    public supportingDocuments: Attachment[]
  ) {}
}
