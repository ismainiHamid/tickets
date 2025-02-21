# Ticket Management System

This project is a backend service for managing tickets, allowing users to create, update, search, and filter tickets.  It provides API endpoints for ticket operations, including saving, updating, and changing ticket statuses.

## Prerequisites

Before running this project, ensure you have the following installed:

*   **Git:** To clone the repository.
*   **Docker:** To run the backend and Oracle database.
*   **Java 11+:** For backend development.
*   **Maven:** For building and managing the project.

## Setup Instructions

### 1. Clone the Repository
### 2. Build the Project:
```bash
mvn clean install
```
### 3. Docker Setup: 
```bash
docker-compose build
```
### 4. Start the containers:
```bash
docker-compose up
```


## Open ai docs:
```bash
openapi: 3.0.1
info:
  title: Ticket Management API
  description: API for managing tickets in the system
  version: 1.0.0
paths:
  /tickets:
    put:
      summary: Save or update tickets
      description: Save or update tickets in the system
      operationId: createOrUpdateTicket
      parameters:
        - name: id
          in: query
          required: false
          description: The ID of the ticket to update (optional for creating new ticket)
          schema:
            type: string
            format: uuid
      requestBody:
        description: Ticket request object to create or update
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/TicketRequestDto'
      responses:
        '201':
          description: Ticket created successfully
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TicketResponseDto'
        '200':
          description: Ticket updated successfully
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TicketResponseDto'

    get:
      summary: Get all tickets
      description: Fetch all tickets with pagination and sorting
      operationId: getAllTickets
      parameters:
        - name: page
          in: query
          description: Page number for pagination
          required: false
          schema:
            type: integer
            default: 0
        - name: size
          in: query
          description: Number of items per page
          required: false
          schema:
            type: integer
            default: 10
        - name: sort
          in: query
          description: Sorting criteria
          required: false
          schema:
            type: array
            items:
              type: string
            default: ["title,DESC"]
      responses:
        '200':
          description: A list of tickets
          content:
            application/json:
              schema:
                type: object
                properties:
                  content:
                    type: array
                    items:
                      $ref: '#/components/schemas/TicketResponseDto'
                  totalElements:
                    type: integer
                  totalPages:
                    type: integer
                  number:
                    type: integer
                  size:
                    type: integer

  /tickets/search:
    get:
      summary: Search & filter tickets
      description: Search and filter tickets by ID and status
      operationId: searchAndFilter
      parameters:
        - name: id
          in: query
          description: Ticket ID to filter by
          required: true
          schema:
            type: string
            format: uuid
        - name: status
          in: query
          description: The status of the ticket to filter by
          required: true
          schema:
            type: string
            enum:
              - OPEN
              - IN_PROGRESS
              - CLOSED
      responses:
        '200':
          description: A list of tickets matching the search criteria
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/TicketResponseDto'

  /tickets/{id}:
    patch:
      summary: Change ticket status
      description: Change the status of a ticket by ID
      operationId: updateTicketStatus
      parameters:
        - name: id
          in: path
          required: true
          description: The ID of the ticket to update
          schema:
            type: string
            format: uuid
        - name: status
          in: query
          required: true
          description: The new status of the ticket
          schema:
            type: string
            enum:
              - OPEN
              - IN_PROGRESS
              - CLOSED
      responses:
        '200':
          description: The ticket status has been updated
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TicketResponseDto'

components:
  schemas:
    TicketRequestDto:
      type: object
      properties:
        title:
          type: string
          description: The title of the ticket
        description:
          type: string
          description: The description of the ticket
        status:
          type: string
          enum:
            - OPEN
            - IN_PROGRESS
            - CLOSED
          description: The status of the ticket
    TicketResponseDto:
      type: object
      properties:
        id:
          type: string
          format: uuid
          description: The unique ID of the ticket
        title:
          type: string
          description: The title of the ticket
        description:
          type: string
          description: The description of the ticket
        status:
          type: string
          enum:
            - OPEN
            - IN_PROGRESS
            - CLOSED
          description: The current status of the ticket
        createdAt:
          type: string
          format: date-time
          description: The date and time when the ticket was created
        updatedAt:
          type: string
          format: date-time
          description: The date and time when the ticket was last updated
    Status:
      type: string
      enum:
        - OPEN
        - IN_PROGRESS
        - CLOSED
      description: The status of the ticket
```
