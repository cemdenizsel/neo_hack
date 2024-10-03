from pydantic import BaseModel


class NFTCreateRequest(BaseModel):
    Receiver: str
    Name: str
    Url: str