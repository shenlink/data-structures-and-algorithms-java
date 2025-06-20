@echo off

REM 执行Maven测试
echo Running Maven tests...
call mvn test

REM 检查测试是否成功
if %ERRORLEVEL% == 0 (
    echo Maven tests passed. Proceeding with git push...

    REM 推送至Gitee
    git push origin master

    REM 推送至GitHub
    git push github master

    echo Code has been successfully pushed to both repositories.
) else (
    echo Maven tests failed. Not pushing to repositories.
)