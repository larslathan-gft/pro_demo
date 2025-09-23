# Spring Boot Demo Project

This is a Spring Boot demo project designed for demonstration or learning purposes.

# Working on Jira Issues

When working with Jira issues, guide the user through the following steps. After each step ask the user for confirmation to proceed to the next one.

1. **Start Working on an Issue:**
   - The user specifies a Jira issue code (e.g., "PROJ-123").
   - Retrieve the issue details from Jira (summary, description, type, priority, etc.) and present them to the user.

2. **Prepare the Work Environment:**
   - Change the Jira issue status to "IN PROGRESS".
   - Suggest creating a new git branch named `feature/[JIRA ISSUE CODE]` if it doesn’t exist, or switching to it if it does. Base new branches off the main branch. Mention to the user the name of the branch.
   - Wait for the user’s confirmation before executing the git tool and updating Jira status.

3. **Plan the Solution:**
   - Explain the issue to the user based on the retrieved Jira details.
   - Outline the proposed steps to resolve the issue, derived from the issue description.

4. **Implement the Changes:**
   - Collaborate with the user to implement the required functionality or fix the bug, suggesting code changes as needed.
   - Ensure the code adheres to these standards:
     - Follows best practices and matches the project’s existing style.
     - Is well-documented with Javadoc comments (e.g., `@param`, `@return`).
     - Includes complete and relevant unit tests.
     - Remains as simple as possible while meeting requirements.
    - Use the coding agent to perform changes in related files together (for example, code and its tests) with one single call to the coding agent tool.

5. **Test the Changes:**
   - After modifying the code, suggest running Maven tests to verify functionality.
   - Execute the tests after user approval and address any failures, try up to two times before consulting the user.

6. **Review the Changes:**
   - Present the diff of all changes to the user.
   - Ask if they approve or if additional modifications are needed.

7. **Commit the Changes:**
   - If the user approves the changes, suggest committing them to the feature branch.
   - Propose a descriptive commit message, and if the commit resolves the issue, include `Closes issue #[JIRA ISSUE CODE]` at the end (e.g., "Implement user login functionality\nCloses issue #PROJ-123").
   - Commit the changes after user confirmation.

8. **Finalize the Issue:**
    - Prepare a comment summarizing the changes (e.g., "Added login endpoint with validation, updated tests, merged via PR #45").
    - Present the comment to the user for approval.
    - After approval, add the comment to the Jira issue and transition its status to "DONE". This ends the work.

**General Guideline:**
- Before performing any significant action (e.g., git commands, Jira updates, code commits), suggest the next step to the user and wait for their validation before proceeding. This ensures the user remains in control and agrees with each action.
- If you get stuck retrying things, ask the user for guidance.
- When showing a list of issued just show the title for faster answers.

# Answering to the user
Your response will be rendered as markdown.
Important: Don't create tasks using the tools.
