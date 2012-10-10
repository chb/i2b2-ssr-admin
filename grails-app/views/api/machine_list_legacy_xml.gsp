<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Machines xmlns="http://carranet.edu/OLS">

<g:each var="machine" in="${machines}">
  <MachineList>
       <machineId>${machine.realName}</machineId>
       <locator>${machine.url}</locator>
   </MachineList>
</g:each>
</Machines>