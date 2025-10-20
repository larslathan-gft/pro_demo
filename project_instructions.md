# Spring Boot Demo Project
 
**Purpose:** Demonstration or learning purposes with Jira and Git integration.
 
---
 
## Workflow Overview
 
This workflow defines the structured SDLC interaction between the user and the assistant.
Each step requires explicit **user confirmation** before proceeding to the next.
 
---
 
### **Step 1 – Start Working on an Issue**
 
**Actions:**
 
* Ask user for the Jira issue code (e.g., `PROJ-123`).
* Retrieve issue details (summary, description, type, priority).
* Display the retrieved details to the user.
 
**Output:**
Show issue details and ask user to confirm to continue.
 
---
 
### **Step 2 – Prepare the Work Environment**
 
**Actions:**
 
* Change Jira issue status to `IN PROGRESS`.
* Suggest creating or switching to a git branch named `feature/[JIRA ISSUE CODE]`.
* Wait for user confirmation before executing git or Jira updates.
 
**Output:**
Print confirmation of branch setup and Jira status change.
 
---
 
### **Step 3 – Plan the Solution**
 
**Actions:**
 
* Explain the issue context based on Jira description.
* Outline proposed steps to resolve it.
 
**Output:**
Display summarized plan and wait for user confirmation.
 
---
 
### **Step 4 – Implement the Changes**
 
**Actions:**
 
* Collaborate with the user to implement functionality or fix bugs.
* Ensure adherence to code standards:
 
  * Follows project’s style and best practices.
  * Includes Javadoc comments (`@param`, `@return`).
  * Contains complete and relevant unit tests.
  * Keeps code as simple as possible.
* Use the coding agent to update related files together (code + tests).
 
**Output:**
Show modified files summary and ask for approval before continuing.
 
---
 
### **Step 5 – Test the Changes**
 
**Actions:**
 
* Suggest running Maven tests.
* Execute tests after user approval.
* Retry fixing failures up to two times, then ask for user help if needed.
 
**Output:**
Display test results summary and next recommendation.
 
---
 
### **Step 6 – Review the Changes**
 
**Actions:**
 
* Present diff of all modified files.
* Ask for user approval or request for more changes.
 
**Output:**
Show diff summary and await user decision.
 
---
 
### **Step 7 – Commit the Changes**
 
**Actions:**
 
* Propose a descriptive commit message.
* If issue resolved, append `Closes issue #[JIRA ISSUE CODE]`.
* Commit after user confirmation.
 
**Output:**
Print commit confirmation message with issue reference.
 
---
 
### **Step 8 – Finalize the Issue**
 
**Actions:**
 
* Draft a summary comment (e.g., “Added login endpoint, updated tests, merged via PR #45”).
* Show comment to user for approval.
* Add comment to Jira issue and mark it as `DONE`.
 
**Output:**
Display final confirmation: Jira updated and workflow completed.
 
---
 
## General Guidelines
 
* Always ask for **user confirmation** before any critical action (git, Jira, commit).
* If stuck, ask for **user guidance** instead of retrying endlessly.
* When listing issues, show **only titles** for faster responses.
* Always print a **visible output message** at the end of each step to confirm completion.
* Responses must be rendered as **Markdown**.
* Do **not** create tasks or automations using tools — only display output messages.