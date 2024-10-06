import asyncio
from data import NFTCreateRequest
from neo3.core import types
from neo3.api.wrappers import ChainFacade, NEP11NonDivisibleContract as ContractWrapper
from neo3.api.helpers.signing import sign_insecure_with_account
from neo3.network.payloads.verification import Signer
from neo3.wallet.account  import Account
from neo3.contracts import nef, manifest
from neo3.wallet import utils


async def interact_with_nft_contract(nft_requeest: NFTCreateRequest)->str:
   
    facade = ChainFacade.node_provider_testnet()  

    # Load the contract files
    nef_file = nef.NEF.from_file("./bin/sc/NFTContract.nef")
    manifest_file = manifest.ContractManifest.from_file(
        "./bin/sc/NFTContract.manifest.json"
    )
    # Load the account. 
    account_wif = "L1HjKr8qvhnfn4pFtn3ScYgQY44UqQFqWcCWZLfrPUgJfC9U36Nm"
    # Select a password for the account. 
    account_password = "%S9!CVY1b%NsMr"
    account = Account.from_wif(account_wif, account_password)

    # Load your contract
    contract_hash = types.UInt160.from_string("0x4d75de76165349c200239dded6e72ba1ba26bc6d")
    contract = ContractWrapper(contract_hash)

    # Add signer to the facade
    facade.add_signer(
        sign_insecure_with_account(account, password=account_password),
        Signer(account.script_hash)
    )

    # Function to mint a new NFT
    async def mint_nft(to_address, name, url):
        result = await facade.invoke(
            contract.call_function("mint", [
                to_address_uint160,
                name,
                url
            ])
        )
        print(result)

    # Example usage
    to_address = "NhFTyXv418ohXmwX8DVXu41Z1t8mSjd6P8"
    to_address_uint160 = utils.address_to_script_hash(to_address)
    name = "My First NFT"
    url = "https://www.worldhistory.org/img/r/p/500x600/11755.jpg?v=1706168947"

    success = await mint_nft(to_address, name, url)
    if success:
        print("NFT minted successfully")
    else:
        return ("Failed to mint NFT")

    # Get the symbol of the NFT
    symbol_result = await facade.test_invoke(contract.symbol)
    return f"NFT Symbol: {symbol_result.stack_item}"

if __name__ == "__main__":
    asyncio.run(main()) 