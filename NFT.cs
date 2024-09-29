using Neo;
using Neo.SmartContract;
using Neo.SmartContract.Framework;
using Neo.SmartContract.Framework.Attributes;
using Neo.SmartContract.Framework.Services;
using System;
using System.ComponentModel;
using System.Numerics;
using System.Runtime.CompilerServices;



namespace Neo.SmartContract.Framework
{
    [SupportedStandards(NepStandard.Nep11)]
    public class NFTContract : Nep11Token<MyTokenState>
    {
        private static readonly StorageMap TokenIndexMap = new StorageMap(Storage.CurrentContext, "TokenIndex");
        private static readonly UInt160 Owner = "NhFTyXv418ohXmwX8DVXu41Z1t8mSjd6P8";
        private static bool IsOwner() => Runtime.CheckWitness(Owner);

        public override string Symbol { [Safe] get => "MNFT"; }

       

        public static bool Mint(UInt160 to,  string name, string url)
        {
            //if (!IsOwner()) throw new Exception("No authorization.");
            if (!to.IsValid) throw new Exception("Amount is invalid!!.");

            BigInteger tokenId = (BigInteger)TokenIndexMap.Get("tokenIndex");
            tokenId += 1;
            TokenIndexMap.Put("tokenIndex", tokenId);

            string tokenIdString = tokenId.ToString();

            MyTokenState token = new MyTokenState(name, url)
            {
                Owner = to
            };

            Mint(tokenIdString, token);
                    return true;
        }
    }

    public class MyTokenState : Nep11TokenState
    {
        public string Image { get; set; }
        public string Name { get; set; }

        public MyTokenState(string name, string url)
        {
            Name = name;
            Image = url;
        }
    }
}