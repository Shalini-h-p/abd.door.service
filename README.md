#Employee Door monitoring Service.

Built with Java Spring Boot

@author: Shalini Hullenahalli Papegowda

@email: shalinihp77@gmail.com

@github: shalini-h-p

#test

Steps

-- Make changes to the project files.

-- Open xampp and start the tomcat server to start Jenkins.

-- Start Ngrok, use the Ngrok command to port forward 8080 port.

-- Change the webhook URL in the GitHub website in the project settings

-- Update the webhook URL with the latest Ngrok port forwarded URL to Jenkins in Manage Jenkins.

-- Push to the main branch in git.

-- This should trigger the build in Jenkins.

-- The final output should be seen at https://{ngrok-url}/exam
