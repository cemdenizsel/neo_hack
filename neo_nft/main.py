from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from data import NFTCreateRequest
from interact_contract import interact_with_nft_contract

app = FastAPI()

@app.post("/nft/create",response_model=str)
async def  create_nft(
    nft_requeest: NFTCreateRequest
):
    return await interact_with_nft_contract(nft_requeest)

app.add_middleware(CORSMiddleware, allow_origins=["*"], allow_methods=["*"], allow_headers=["*"],
                   allow_credentials=True)
                   
if __name__ == "__main__":
    import uvicorn
    from os import getenv

    host = getenv("HOST", "0.0.0.0")
    port = int(getenv("PORT", "8084"))  # Default port is 8080 if not specified
    uvicorn.run(app, host=host, port=port)