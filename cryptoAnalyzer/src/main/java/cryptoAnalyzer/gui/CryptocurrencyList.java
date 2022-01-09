package cryptoAnalyzer.gui;

/**
 * The Class contains the allowList and showList in the UI
 * @author Xiaoke
 *
 */
public class CryptocurrencyList {

	/**
	 * The allowList that contains all the string id
	 */
	private static String[] allowList = {"Bitcoin",
			"Ethereum",
			"Tether",
			"Solana",
			"Cardano",
			"Dogecoin",
			"ShibaInu",
			"Litecoin",
			"Chainlink",
			"Bitcoin Cash",
			"Dai",
			"Uniswap",
			"Axie Infinity",
			"Cosmos",
			"TerraUSD",
			"Stellar",
			"VeChain",
			"OKB",
			"cETH",
			"TRON",
			"FTX Token",
			"Elrond",
			"Lido Staked Ether",
			"Internet Computer",
			"Theta Network",
			"Ethereum Classic",
			"Hedera",
			"The Sandbox",
			"Filecoin",
			"cDAI",
			"Decentraland",
			"Near",
			"Fantom",
			"Monero",
			"cUSDC",
			"Tezos",
			"The Graph",
			"Gala",
			"Magic Internet Money",
			"Klaytn",
			"Radix",
			"IOTA",
			"Flow",
			"EOS",
			"Helium",
			"Olympus",
			"LEO Token",
			"PancakeSwap",
			"Kusama",
			"Aave",
			"Loopring",
			"THORChain",
			"Enjin Coin",
			"Amp",
			"Stacks",
			"Bitcoin SV",
			"eCash",
			"Quant",
			"Arweave",
			"Harmony",
			"Huobi BTC",
			"Waves",
			"Chiliz",
			"KuCoin Token",
			"LINK",
			"Wonderland",
			"ECOMI",
			"NEM",
			"IoTeX"};
	
	/**
	 * The shown list contains
	 */
	private static String[] shownList = {"Invalid Token","Bitcoin",
			"Ethereum",
			"Tether",
			"Solana",
			"Cardano",
			"Dogecoin",
			"ShibaInu",
			"Litecoin",
			"Chainlink",
			"Bitcoin Cash",
			"Dai",
			"Uniswap",
			"Axie Infinity",
			"Cosmos",
			"TerraUSD",
			"Stellar",
			"VeChain",
			"OKB",
			"cETH",
			"TRON",
			"FTX Token",
			"Elrond",
			"Lido Staked Ether",
			"Internet Computer",
			"Theta Network",
			"Ethereum Classic",
			"Hedera",
			"The Sandbox",
			"Filecoin",
			"cDAI",
			"Decentraland",
			"Near",
			"Fantom",
			"Monero",
			"cUSDC",
			"Tezos",
			"The Graph",
			"Gala",
			"Magic Internet Money",
			"Klaytn",
			"Radix",
			"IOTA",
			"Flow",
			"EOS",
			"Helium",
			"Olympus",
			"LEO Token",
			"PancakeSwap",
			"Kusama",
			"Aave",
			"Loopring",
			"THORChain",
			"Enjin Coin",
			"Amp",
			"Stacks",
			"Bitcoin SV",
			"eCash",
			"Quant",
			"Arweave",
			"Harmony",
			"Huobi BTC",
			"Waves",
			"Chiliz",
			"KuCoin Token",
			"LINK",
			"Wonderland",
			"ECOMI",
			"NEM",
			"IoTeX"};
	
	/**
	 * Get method to get a String array contains all the allowed cryptocurrency
	 * @return return String[] object 
	 */
	public static String[] getAllowList() {
		return allowList;
	}
	
	/**
	 * Get method to get a String array contains all the cryptocurrency that will shown.
	 * @return return String[] object
	 */
	public static String[] shownList() {
		return shownList;
	}
}
