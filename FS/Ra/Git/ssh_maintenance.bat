@echo off
cls

:: environment variable SSH_AUTH_SOCK to properly work:
set SSH_AUTH_SOCK=%TEMP%\ssh-agent.socket
set ssh_agent_out_params_file=%SSH_AUTH_SOCK%_params

:: check if ssh-agent already runned:
set ssh-agent_already_runned=NO
for /f %%a in ('tasklist /NH /FI "IMAGENAME eq ssh-agent.exe"') do (
  set ssh-agent_already_runned=YES
)

:: start ssh-agent if it didn't run:
if %ssh-agent_already_runned%==NO (
  del /A:AS /Q %SSH_AUTH_SOCK%
  ssh-agent -a %SSH_AUTH_SOCK% > %ssh_agent_out_params_file%
)

:: get and set environment variable SSH_AGENT_PID:
set SSH_AGENT_PID=0
for /f "delims=;" %%a in (%ssh_agent_out_params_file%) do (
  for /f "tokens=1* delims==" %%b in ("%%a") do (
    if %%b==SSH_AGENT_PID SET SSH_AGENT_PID=%%c
  )
)

:: check if key already in memory:
set key=p:\.ssh\id_rsa
set loaded_keys_filename=%SSH_AUTH_SOCK%_loaded_keys

ssh-add -l | find "%key%" > %loaded_keys_filename%

set key_already_loaded=NO
for /f %%a in (%loaded_keys_filename%) do (
  set key_already_loaded=YES
)

:: if key is not loaded - load it:
if %key_already_loaded%==NO (
  ssh-add %key%
)

:: close ss-agent:
rem ssh-agent -k
