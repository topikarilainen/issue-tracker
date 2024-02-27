// Corresponds to the serialized representation of Issue objects as provided by the backend 
export interface Issue {
    id: number;
    type: IssueType;
    priority: number;
    title: string;
    description: string;
    creator: User;
    assignee: User;
    status: IssueStatus;
    projectAbbreviation: string;
}

// Corresponds to the serialized representation of Project objects as provided by the backend 
export interface Project {
    id: number;
    name: string;
    abbreviation: string;
    issues: Issue[];
    users: Set<User>;
}

// Corresponds to the serialized representation of User objects as provided by the backend 
export interface User {
    id: number;
    username: string;
    fullName: string;
}

export type IssueType = 'Bug' | 'Feature' | 'Idea';
export type IssueStatus = 'Open' | 'In progress' | 'Closed';
