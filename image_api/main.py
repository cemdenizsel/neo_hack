
from fastapi.middleware.cors import CORSMiddleware
from fastapi import FastAPI,APIRouter
from controller import image

app = FastAPI(
    title="API Project",
    description="Work in progress",
    version='0.1',
    swagger_ui_parameters={"docExpansion": "none"},
)


routers = [
    image.router
]
for router in routers:
    app.include_router(router)
app.add_middleware(CORSMiddleware, allow_origins=["*"], allow_methods=["*"], allow_headers=["*"],
                   allow_credentials=True)

if __name__ == "__main__":
    import uvicorn
    from os import getenv

    host = getenv("HOST", "0.0.0.0")
    port = int(getenv("PORT", "8089"))  # Default port is 8080 if not specified
    uvicorn.run(app, host=host, port=port)
